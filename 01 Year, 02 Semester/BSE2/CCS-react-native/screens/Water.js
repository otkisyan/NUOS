import { StatusBar } from "expo-status-bar";
import { useEffect, useRef, useState } from "react";
import {
  StyleSheet,
  Text,
  SafeAreaView,
  Image,
  TextInput,
  View,
  TouchableHighlight,
  TouchableOpacity,
  TouchableNativeFeedback,
  Dimensions,
} from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";

const windowWidth = Dimensions.get("window").width;
const windowHeight = Dimensions.get("window").height;

export default function WaterScreen({ navigation }) {
  let [PreviousEntryValue, setPreviousEntryValue] = useState("");
  let [NewEntryValue, setNewEntryValue] = useState("");
  let [RateValue, setRateValue] = useState("");
  let [Entries, setEntries] = useState([]);
  let ResultValue = findSum();

  function findSum() {
    NewEntryValue = NewEntryValue.replace(/,/g, ".");
    PreviousEntryValue = PreviousEntryValue.replace(/,/g, ".");
    RateValue = RateValue.replace(/,/g, ".");

    let result = ((NewEntryValue - PreviousEntryValue) * RateValue).toFixed(2);
    return result;
  }

  const getData = async () => {
    try {
      const jsonValue = await AsyncStorage.getItem("@WaterEntries");
      console.log(jsonValue);
      return jsonValue != null ? setEntries(JSON.parse(jsonValue)) : null;
    } catch (e) {
      console.log(e);
    }
  };

  const storeData = async () => {
    try {
      const jsonValue = JSON.stringify(Entries);
      await AsyncStorage.setItem("@WaterEntries", jsonValue);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getData();
    //AsyncStorage.clear();
  }, []);

  useEffect(() => {
    storeData();
    console.log(Entries);
  }, [Entries]);

  const onPressHandler = () => {
    if (PreviousEntryValue != "" || NewEntryValue != "" || RateValue != "") {
      setEntries([
        ...Entries,
        {
          id: Entries.length.toString(),
          PreviousEntryValue: PreviousEntryValue,
          NewEntryValue: NewEntryValue,
          RateValue: RateValue,
          ResultValue: ResultValue.toString(),
        },
      ]);
      console.log(PreviousEntryValue, NewEntryValue, RateValue, ResultValue);
    }
  };
  return (
    <SafeAreaView style={styles.container}>
      <TouchableOpacity
        style={styles.backContainer}
        onPress={() => navigation.navigate("Home")}
      >
        <Image
          source={require("../assets/back-icon.png")}
          style={styles.back}
        />
      </TouchableOpacity>
      <Text style={styles.title}>ВОДА</Text>
      <Text style={styles.previousEntry}>Попередні показання</Text>
      <TextInput
        style={styles.input}
        placeholder="0000"
        onChangeText={(value) => setPreviousEntryValue(value)}
        returnKeyType="done"
        keyboardType="numeric"
      ></TextInput>

      <Text style={styles.newEntry}>Нові показання</Text>
      <TextInput
        style={styles.input}
        placeholder="0000"
        onChangeText={(value) => setNewEntryValue(value)}
        returnKeyType="done"
        keyboardType="numeric"
      ></TextInput>
      <Text style={styles.rate}>Тариф</Text>
      <TextInput
        style={styles.input}
        placeholder="0000"
        onChangeText={(value) => setRateValue(value)}
        returnKeyType="done"
        keyboardType="numeric"
      ></TextInput>
      <Text style={styles.sum}>Сума до сплати</Text>
      <TextInput
        style={styles.output}
        placeholder="0000"
        editable={false}
        value={ResultValue.toString()}
      ></TextInput>

      <TouchableOpacity
        onPress={() => {
          onPressHandler();
        }}
        style={styles.button}
      >
        <Text style={styles.buttonText}>Зберегти до архіву</Text>
      </TouchableOpacity>

      <TouchableOpacity
        onPress={() => {
          navigation.navigate("WaterEntries");
        }}
      >
        <Text style={styles.history}>Історія показань</Text>
      </TouchableOpacity>
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
    top: "3.5%",
    right: "40%",
  },

  title: {
    textAlign: "center",
    fontFamily: "InterMedium",
    fontSize: 24,
  },
  previousEntry: {
    marginTop: "10%",
    fontSize: 18,
    fontFamily: "InterMedium",
    textAlign: "center",
  },
  newEntry: {
    marginTop: "5%",
    fontSize: 18,
    fontFamily: "InterMedium",
    textAlign: "center",
  },
  rate: {
    marginTop: "5%",
    fontSize: 18,
    fontFamily: "InterMedium",
    textAlign: "center",
  },
  sum: {
    marginTop: "10%",
    fontSize: 18,
    fontFamily: "InterMedium",
    textAlign: "center",
  },

  input: {
    marginTop: "2%",
    height: 40,
    margin: 12,
    borderBottomWidth: 1,
    padding: 10,
  },
  output: {
    marginTop: "2%",
    height: 40,
    margin: 12,
    borderBottomWidth: 1,
    padding: 10,
  },
  button: {
    alignItems: "center",
    justifyContent: "center",
    textAlign: "center",
    marginTop: "5%",
    margin: 5,
    width: 160,
    height: 50,
    backgroundColor: "#197BDD",
    borderRadius: 10,
    border: 0,
  },
  buttonText: {
    fontFamily: "InterBold",
    color: "white",
  },
  history: {
    marginTop: "5%",
    fontFamily: "InterBold",
    fontSize: 12,
    color: "rgba(0, 0, 0, 0.37)",
  },
});
