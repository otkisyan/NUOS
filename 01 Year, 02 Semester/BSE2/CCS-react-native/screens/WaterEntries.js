import { StatusBar } from "expo-status-bar";
import { useEffect, useRef, useState } from "react";
import {
  StyleSheet,
  Text,
  SafeAreaView,
  Image,
  Button,
  TextInput,
  Dimensions,
  TouchableHighlight,
  TouchableOpacity,
  TouchableNativeFeedback,
} from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

const windowWidth = Dimensions.get("window").width;
const windowHeight = Dimensions.get("window").height;

export default function WaterEntries({ navigation }) {
  let [Entries, setEntries] = useState([]);

  const getData = async () => {
    try {
      const jsonValue = await AsyncStorage.getItem("@WaterEntries");
      console.log(jsonValue);
      return jsonValue != null ? setEntries(JSON.parse(jsonValue)) : null;
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getData();
    //AsyncStorage.clear();
  }, []);

  return (
    <SafeAreaView style={styles.container}>
      <TouchableOpacity
        style={styles.backContainer}
        onPress={() => navigation.navigate("Water")}
      >
        <Image
          source={require("../assets/back-icon.png")}
          style={styles.back}
        />
      </TouchableOpacity>
      <Text style={styles.title}>Історія показань</Text>
      <Text style={styles.subtitle}>Вода</Text>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
  },
  back: {
    width: 24,
    height: 24,
  },

  backContainer: {
    position: "relative",
    top: "4%",
    right: "40%",
  },

  title: {
    textAlign: "center",
    fontFamily: "InterMedium",
    fontSize: 24,
  },

  subtitle: {
    fontSize: 18,
    marginTop: 10,
    fontFamily: "InterMedium",
    color: "rgba(25, 123, 221, 1)",
  },
});
