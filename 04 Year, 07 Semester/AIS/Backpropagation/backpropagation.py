import os
import numpy as np
from PIL import Image, ImageTk
import tkinter as tk
from tkinter import filedialog

# Global variable for class labels mapping
class_labels = {"2": 0, "3": 1, "4": 2, "5": 3, "7": 4}

class NeuralNetwork:
    def __init__(self, input_size, hidden_size, output_size):
        # Initialize weights with small random values
        #
        # weight matrix that connects the input layer to the hidden layer
        # The shape of this matrix is (input_size, hidden_size)
        # where:
        # - input_size refers to the number of input features (neurons in the input layer),
        #   i.e., the number of features per input example (for a 10x10 image flattened to 100 pixels, input_size = 100)
        # - hidden_size refers to the number of neurons in the hidden layer.
        # This matrix stores the weights for each input feature (row) and each hidden neuron (column),
        # meaning that each input feature is connected to each hidden neuron.
        self.weights_input_hidden = np.random.uniform(-0.3, 0.3, (input_size, hidden_size))
        
        # weight matrix that connects the hidden layer to the output layer
        # The shape of this matrix is (hidden_size, output_size)
        # where:
        # - hidden_size is the number of neurons in the hidden layer,
        # - output_size is the number of neurons in the output layer (i.e., the number of output classes or predictions).
        # This matrix stores the weights for each hidden neuron (row) and each output neuron (column),
        # meaning that each hidden layer neuron is connected to each output neuron.
        self.weights_hidden_output = np.random.uniform(-0.3, 0.3, (hidden_size, output_size))

    # vectorized operations
    def sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

    def sigmoid_derivative(self, x):
        return x * (1 - x)

    def forward(self, X):
        # For two matrices A (shape: [m, n]) and B (shape: [n, p]), 
        # the multiplication A * B is valid only if the number of columns in A (n) equals the number of rows in B (n).
        # The resulting matrix will have the number of rows of the first matrix 
        # and the number of columns of the second matrix.The resulting matrix will have shape [m, p].
        # 
        # batch_size represents the number of samples (or data points) in a batch that the network processes simultaneously.
        # In the input matrix X (shape: [batch_size, input_size]), the batch_size corresponds to the number of rows,
        # where each row is a separate example (input data) that will be passed through the network.
        #
        # Input to hidden layer propagation:
        # Multiply input data X (shape: [batch_size, input_size]) with the weight matrix connecting the input to the hidden layer
        # (shape: [input_size, hidden_size]). The result (shape: [batch_size, hidden_size]) represents the inputs to each
        # hidden layer neuron for each example in the batch.
        #
        # The multiplication is valid if input_size (number of columns in X) 
        # equals the number of rows in the weight matrix (input_size in self.weights_input_hidden).
        # The result will have shape [batch_size, hidden_size].
        hidden_input = np.dot(X, self.weights_input_hidden)
        hidden_output = self.sigmoid(hidden_input)

        # Hidden to output layer propagation:
        # Multiply the output from the hidden layer (shape: [batch_size, hidden_size]) with the weight matrix connecting the
        # hidden layer to the output layer (shape: [hidden_size, output_size]). The result (shape: [batch_size, output_size])
        # represents the inputs to each output layer neuron for each example in the batch.
        #
        # The multiplication is valid if hidden_size (number of columns in hidden_output) equals the number of rows in the
        # weight matrix (hidden_size in self.weights_hidden_output). The result will have shape [batch_size, output_size].
        final_input = np.dot(hidden_output, self.weights_hidden_output)
        final_output = self.sigmoid(final_input)
        # print(final_output[2])

        # Return final output along with intermediate values needed for backward pass
        return final_output, hidden_output

    def backward(self, X, y, output, hidden_output, learning_rate):
        # Output layer error:
        # The error for each output neuron is the difference between the expected output (y) and the predicted output (output).
        # This error indicates how much the prediction deviates from the true value.
        # The size of output_error will be [batch_size, output_size], where:
        # - batch_size is the number of input examples in the batch,
        # - output_size is the number of neurons in the output layer (the number of classes or predicted values).
        output_error = output - y;
        
        # The delta for the output layer is the element-wise product of the output error and the derivative of the sigmoid function.
        # The sigmoid_derivative function returns the gradient of the sigmoid activation function.
        # This tells us how much we should adjust the weights for the output layer based on the error.
        # The size of output_delta will be the same as output_error: [batch_size, output_size].
        output_delta = output_error * self.sigmoid_derivative(output)

        # Update weights for output layer
        # We use 'hidden_output' here because we are updating the weights that connect the hidden layer to the output layer.
        # The 'hidden_output' matrix represents the activations (outputs) of the neurons in the hidden layer after the forward pass.
        # These values determine how much influence each hidden neuron has on the final output predictions.
        # By using 'hidden_output.T', we ensure that the weight updates are correctly aligned to account for the contribution
        # of each hidden neuron to each output neuron based on the calculated output error (represented by 'output_delta').
        #
        # hidden_output (shape: [batch_size, hidden_size]) represents the activations of the hidden neurons.
        # We transpose this matrix (hidden_output.T) to have shape [hidden_size, batch_size] for the matrix multiplication.
        # output_delta (shape: [batch_size, output_size]) contains the gradients (deltas) for the output layer errors.
        # The dot product of hidden_output.T (shape: [hidden_size, batch_size]) and output_delta (shape: [batch_size, output_size])
        # results in a matrix of shape [hidden_size, output_size], which matches the shape of the weights connecting
        # the hidden layer to the output layer (self.weights_hidden_output). 
        self.weights_hidden_output -= learning_rate * np.dot(hidden_output.T, output_delta)

        # Update weights for hidden layer
        # We use X (input data) here because we are updating the weights that connect the input layer to the hidden layer.
        # The goal is to determine how each input feature contributes to the error of the hidden layer neurons.
        # X contains the original input values, which represent how much influence each feature should have on the hidden layer adjustments.
        # Using X allows us to properly backpropagate the error signal from the hidden layer to the input layer weights.
        
        # Hidden layer error:
        # The error for each hidden neuron is calculated by propagating the error from the output layer backwards.
        # We compute the dot product of the output delta and the weight matrix that connects the hidden layer to the output layer.
        # This step transfers the error information from the output layer to the hidden layer.
        # The weights between hidden and output are of size [hidden_size, output_size], so we need to transpose
        # this weight matrix to perform the matrix multiplication correctly.
        # The resulting hidden_error will have shape [batch_size, hidden_size].
        # Weighted sum of delta_k * v_jk
        hidden_error = np.dot(output_delta, self.weights_hidden_output.T)
        
        # The delta for the hidden layer is the element-wise product of the hidden error and the derivative of the sigmoid function.
        # This tells us how much we should adjust the weights for the hidden layer based on the error propagated from the output layer.
        # The size of hidden_delta will be the same as hidden_error: [batch_size, hidden_size].
        hidden_delta = hidden_error * self.sigmoid_derivative(hidden_output)
        
        # X (shape: [batch_size, input_size]) represents the input data.
        # We transpose X (X.T) to have shape [input_size, batch_size] for the matrix multiplication.
        # hidden_delta (shape: [batch_size, hidden_size]) contains the gradients (deltas) for the hidden layer errors.
        # The dot product of X.T (shape: [input_size, batch_size]) and hidden_delta (shape: [batch_size, hidden_size])
        # results in a matrix of shape [input_size, hidden_size], which matches the shape of the weights connecting
        # the input layer to the hidden layer (self.weights_input_hidden). 
        self.weights_input_hidden -= learning_rate * np.dot(X.T, hidden_delta)

    def train(self, X, y, epochs, learning_rate, error_threshold=0.1):
        for epoch in range(epochs):
            # Forward propagation
            output, hidden_output = self.forward(X)

            # Backward propagation and weight updates
            self.backward(X, y, output, hidden_output, learning_rate)

            # Print loss for every 100th epoch
            if epoch == 0 or (epoch + 1) % 100 == 0:
                loss = 0.5 * np.sum((y - output) ** 2)
                print(f"Epoch {epoch}, Loss: {loss}")
            
            # If the error is less than the threshold, stop training
            if loss < error_threshold:
                print(f"Training stopped early at epoch {epoch} with loss {loss}")
                break

    def predict(self, X):
        output, _ = self.forward(X)
        return output

class ImageProcessor:
    def __init__(self, folder_path, image_size=(10, 10)):
        self.folder_path = folder_path
        self.image_size = image_size

    def load_images_from_folder(self):
        images = []
        labels = []
        selected_classes = set(class_labels.keys())
        
        # Traverse the main directory
        for class_name in os.listdir(self.folder_path):
            if class_name in selected_classes:  # Only process folders of interest
                class_folder = os.path.join(self.folder_path, class_name)
                if os.path.isdir(class_folder):
                    for filename in os.listdir(class_folder):
                        img_path = os.path.join(class_folder, filename)
                        
                        # By converting an image to grayscale, we convert it to a monochrome image 
                        # where each pixel represents only one luminance value instead of three (RGB).
                        # This reduces the dimensionality of the data, making it easier to work with.
                        img = Image.open(img_path).convert('L')
                        
                        # Normalizing to the range [0, 1] allows the values 
                        # to be transformed so that all pixels are between 0 and 1.
                        # This is done to speed up and improve the training of the neural network.
                        img_array = np.asarray(img).flatten() / 255.0  
                        images.append(img_array)
                        labels.append(class_labels[class_name])  # Assign label

        images = np.array(images)
        labels = np.array(labels)
        return images, labels

    def load_ideal_images(self):
        ideal_images = []
        for class_name in class_labels.keys():
            class_folder = os.path.join(self.folder_path, class_name)
            if os.path.isdir(class_folder):
                img_path = os.path.join(class_folder, os.listdir(class_folder)[0])
                img = Image.open(img_path).convert('L').resize(self.image_size)
                img_tk = ImageTk.PhotoImage(img.resize((100, 100)))  # Resize for display
                ideal_images.append(img_tk)
        return ideal_images

class DigitClassificationApp:
    def __init__(self, root, nn, image_processor):
        self.root = root
        self.nn = nn
        self.image_processor = image_processor
        self.class_labels = class_labels
        
        # GUI Setup
        self.setup_gui()

    def setup_gui(self):
        self.root.title("Digit Classification")

        # Load button
        load_btn = tk.Button(self.root, text="Load Test Image", command=self.load_test_image)
        load_btn.pack()

        # Label to display the test image
        self.test_img_label = tk.Label(self.root)
        self.test_img_label.pack()

        # Frame for displaying class images and similarity scores
        frame = tk.Frame(self.root)
        frame.pack()

        # Load ideal images
        self.ideal_images_tk = self.image_processor.load_ideal_images()

        # Update display for each class image
        self.score_labels = []
        for i, img_tk in enumerate(self.ideal_images_tk):
            panel = tk.Label(frame, image=img_tk)
            panel.grid(row=0, column=i)

            label = tk.Label(frame, text=f"Class {list(self.class_labels.values())[i]}: --%")
            label.grid(row=1, column=i)
            self.score_labels.append(label)

    def load_test_image(self):
        file_path = filedialog.askopenfilename()
        if file_path:
            img = Image.open(file_path).convert('L').resize((10, 10))
            img_array = np.asarray(img).flatten() / 255.0
            self.display_test_image(img)
            
            # Predict the class probabilities for the input image
            # The input image is wrapped in an array (np.array([img_array])) to create a batch of size 1,
            # ensuring it matches the expected input shape for the network (2D array with shape (1, input_size)).
            # The output `predictions` is also a batch of predictions, and since the batch size is 1,
            # `predictions` will be an array of shape (1, output_size), where `output_size` is the number of possible classes
            # the model can predict (in this case, 5 classes corresponding to digits 2, 3, 4, 5, and 7).
            # `predictions[0]` contains the predictions (probabilities) for the single input image.
            predictions = self.nn.predict(np.array([img_array]))
            self.display_similarity_scores(predictions[0])

    def display_test_image(self, img):
        img_tk = ImageTk.PhotoImage(img.resize((100, 100)))
        self.test_img_label.configure(image=img_tk)
        self.test_img_label.image = img_tk

    def display_similarity_scores(self, predictions):
        for i, (label, score) in enumerate(zip(self.class_labels.values(), predictions)):
            self.score_labels[i].config(text=f"Class {label}: {score * 100:.2f}%")
            
# One-Hot Encoding is used to convert class labels into binary vectors.
# This prevents the network from misinterpreting class labels as ordinal values (e.g., 2 < 3 < 4).
# Each class label is represented by a vector with a 1 in the corresponding class position and 0s elsewhere.
# This allows the neural network to treat each class as a distinct category and improves the training process.
# The encoded vectors are used for loss calculation and output comparison in multi-class classification tasks. 
def one_hot_encode(y):
    # Create a zero-filled matrix with shape (number of samples, number of classes)
    # The number of classes is determined by the maximum value in `y` (`y.max() + 1`)
    # This ensures that each class has a corresponding column in the encoded matrix
    y_encoded = np.zeros((y.size, y.max() + 1))
    
    # Iterate over each label in y and set 1 in the corresponding column
    for i in range(y.size):
        # Set 1 in the corresponding column (y[i]) for the i-th row in y_encoded
        # y[i] represents the class label for the i-th sample
        y_encoded[i, y[i]] = 1
        # print(y_encoded[i])
    
    return y_encoded

def main():
    # Set up folder path and data
    folder_path = "/Users/otkisyan/Temp/10x10-dataset-2"
    image_processor = ImageProcessor(folder_path)
    x, y = image_processor.load_images_from_folder()

    y_encoded = one_hot_encode(y)

    # Neural Network setup
    # input_size refers to the the dimensionality of the input data that the neural network expects.
    # Each image is 10x10 pixels (grayscale), so when flattened, 
    # the image becomes a 1D array with 100 values (one for each pixel).
    input_size = 100
    hidden_size = 64
    
    # output_size = 5 represents the number of possible output classes.
    # In this case, there are 5 classes corresponding to the digits 2, 3, 4, 5, and 7.
    # The neural network has 5 output neurons, one for each class, to predict the class of the input image.
    output_size = 5
    nn = NeuralNetwork(input_size, hidden_size, output_size)

    # Train the neural network
    epochs = 1000
    learning_rate = 0.01
    print("Starting training...")
    nn.train(x, y_encoded, epochs, learning_rate)
    print("Training complete!")

    # GUI setup
    root = tk.Tk()
    app = DigitClassificationApp(root, nn, image_processor)
    root.mainloop()

if __name__ == "__main__":
    main()
