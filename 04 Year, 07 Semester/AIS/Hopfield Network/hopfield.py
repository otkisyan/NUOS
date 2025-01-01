import os
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

# Dictionary to map folder names to class labels
class_labels = {"2": 0, "3": 1, "4": 2}

class ImageProcessor:
    def __init__(self, path, image_size=(10, 10)):
        self.folder_path = path
        self.image_size = image_size

    def load_images_from_folder(self):
        images = []
        selected_classes = set(class_labels.keys())

        # Traverse the main directory
        for class_name in os.listdir(self.folder_path):
            if class_name in selected_classes:  # Only process folders of interest
                class_folder = os.path.join(self.folder_path, class_name)
                for file_name in os.listdir(class_folder):
                    if file_name.lower().endswith('.jpg'):  # Check if file is a .jpg
                        file_path = os.path.join(class_folder, file_name)
                        if os.path.isfile(file_path):
                            img = self.load_and_preprocess_image(file_path)
                            images.append(img)

        return np.array(images)

    def load_and_preprocess_image(self, file_path):
        """Load an image, resize, and convert to binary array."""
        img = Image.open(file_path)
        img = img.resize(self.image_size)
        img = img.convert('1') # convert image to black and white
        img_array = 2 * np.array(img, int) - 1  # Convert to -1 and 1
        return img_array.flatten()
    
    def load_images_by_names(self, file_paths):
        """Load and preprocess images by a list of file paths."""
        images = []
        for file_path in file_paths:
            if os.path.isfile(file_path):
                img = self.load_and_preprocess_image(file_path)
                images.append(img)
        return np.array(images)

class HopfieldNetwork:
    def __init__(self, n_neurons):
        self.n_neurons = n_neurons
        self.weights = np.zeros((n_neurons, n_neurons))

    def train(self, patterns):
        # Patterns shape is (num_patterns, num_neurons)
        for pattern in patterns:
            for i in range(self.n_neurons):
                for j in range(self.n_neurons):
                    if i != j:
                        self.weights[i, j] += pattern[i] * pattern[j]
        # print(self.weights)
                        
        # for pattern in patterns:
        #     self.weights += np.outer(pattern, pattern)
        # np.fill_diagonal(self.weights, 0)  # No self-connections
        
    # def reconstruct(self, state):
    #     e = 0.0001
    #     prev_state = state.copy()
    #     for i in range(self.n_neurons):
    #         S_j = 0 # the total input for the j-th neuron,
    #         for j in range(self.n_neurons):
    #             if i != j:  
    #                 S_j += state[i] * self.weights[i, j]
                    
    #     # binary/bipolar activation function
    #     for iteration in range(self.n_neurons):
    #         if S_j > e:
    #             state[iteration] = 1
    #         elif S_j < -e:
    #             state[iteration] = -1

    #         if np.array_equal(state, prev_state):  # No changes in state
    #             print(f"Stabilized after {iteration + 1} iterations.")
    #             break
            
    #     return state
    
    
    def reconstruct(self, image):
        for _ in range(self.n_neurons):
            prev_image = image.copy()
            image = np.sign(np.dot(image, self.weights))
            if np.array_equal(image, prev_image):
                break
        return image
    
    # def reconstruct(self, state):
    #     while True:  
    #         previous_state = state.copy() 
            
    #         for i in range(self.n_neurons):
    #             S_j = 0 
    #             for j in range(self.n_neurons):
    #                 if i != j:
    #                     S_j += state[j] * self.weights[i, j]

    #             if S_j > 0:
    #                 state[i] = 1
    #             elif S_j < 0:
    #                 state[i] = -1
            
    #         if np.array_equal(state, previous_state):
    #             break  # Стан стабілізувався
    
    #     return state


    
    # def reconstruct(self, state):
            
    #     for j in range(self.n_neurons):
    #         S_j = 0  # the total input for the j-th neuron
    #         for i in range(self.n_neurons):
    #             if i != j:
    #                 S_j += state[i] * self.weights[j, i]
                
    #             # Binary/bipolar activation function
    #         if S_j > 0:
    #             state[j] = 1
    #         elif S_j < 0:
    #             state[j] = -1
                
    #     return state

    # def modify_image(self, image, portion=0.5):
    #     """Modify a portion of the image to be negative."""
    #     num_pixels_to_modify = int(len(image) * portion)
    #     indices_to_modify = np.random.choice(len(image), num_pixels_to_modify, replace=False)
    #     image[indices_to_modify] = -1
    #     #image[:num_pixels_to_modify] = -1
    #     return image
    
    
    def modify_image(self, image, portion=0.5, noise_intensity=0.1):
        # Invert a random portion of the image
        num_pixels_to_modify = int(len(image) * portion)
        indices_to_modify = np.random.choice(len(image), num_pixels_to_modify, replace=False)
        image[indices_to_modify] = -1

        # Add noise to the background
        background_indices = np.where(image == -1)[0]  # Find indices of the background
        num_noise_pixels = int(len(background_indices) * noise_intensity)
        if num_noise_pixels > 0:
            noise_indices = np.random.choice(background_indices, num_noise_pixels, replace=False)
            image[noise_indices] = 1  # Add noise by flipping background pixels to foreground

        return image

    
class Interface:
    
    @staticmethod
    def display_image(image, title="Image"):
        side = int(np.sqrt(image.shape[0]))
        img_array = image.reshape((side, side))
        plt.figure(figsize=(3, 3))
        plt.imshow(img_array, cmap='gray')
        plt.title(title)
        plt.axis('off')
        plt.show()
    
    @staticmethod
    def show_multiple_arrays(img_arrays):
        num_images = len(img_arrays)
        for i in range(num_images):
            side = int(np.sqrt(img_arrays[i].shape[0]))
            plt.subplot(1, num_images, i + 1)
            plt.imshow(img_arrays[i].reshape((side, side)), cmap='gray')
            plt.axis('off')
        plt.show()

    def run_test(self, network, test_image, portion):
        self.display_image(test_image, title="Original Image")

        modified_image = network.modify_image(test_image.copy(), portion)
        self.display_image(modified_image, title="Modified Image")

        reconstructed_image = network.reconstruct(modified_image.copy())
        self.display_image(reconstructed_image, title="Reconstructed Image")

if __name__ == "__main__":
    np.set_printoptions(threshold=np.inf)
    # Set up paths and initialize processor
    folder_path = "/Users/otkisyan/Temp/10x10-dataset-2/hopfield"
    img_processor = ImageProcessor(folder_path, image_size=(10, 10))
    
    # Load images and labels
    images = img_processor.load_images_from_folder()
    print(np.array2string(images, separator=', '))
    # file_paths = [f'{folder_path}/{i}.jpg' for i in range(4)]
    # images = img_processor.load_images_by_names(file_paths)
    print(f"Loaded {len(images)} images.")

    # Initialize Hopfield network
    n_neurons = images.shape[1]  # Total number of pixels in an image (100 for 10x10)
    hopfield_net = HopfieldNetwork(n_neurons)

    # Train the network with all images
    hopfield_net.train(images)
    print("Training completed.")
    
    test_img = img_processor.load_and_preprocess_image(
        "/Users/otkisyan/Temp/10x10-dataset-2/hopfield/test/2/0.jpg")

    interface = Interface()
    interface.show_multiple_arrays(images)
    interface.run_test(hopfield_net, test_img, portion=0.2)