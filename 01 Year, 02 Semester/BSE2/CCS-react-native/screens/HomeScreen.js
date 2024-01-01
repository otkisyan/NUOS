import { StatusBar } from "expo-status-bar";
import {
  StyleSheet,
  Text,
  SafeAreaView,
  Image,
  Button,
  PixelRatio,
  TouchableHighlight,
  TouchableOpacity,
  TouchableNativeFeedback,
  Dimensions,
} from "react-native";
import { useTranslation } from "react-i18next";

const windowWidth = Dimensions.get("window").width;
const windowHeight = Dimensions.get("window").height;

let titleFontSize = 24;
let imageHeight = "30%";

if (windowHeight <= 667) {
  titleFontSize = 20;
}

if (windowHeight <= 667) {
  imageHeight = "25%";
}

export default function HomeScreen({ navigation }) {
  const { t, i18n } = useTranslation();
  return (
    <SafeAreaView style={styles.container}>
      <Image source={require("../assets/avatar.png")} style={styles.image} />
      <Text style={styles.title}>{t("title")}</Text>
      <Text style={styles.subtitle}>{t("subtitle")}</Text>
      <SafeAreaView style={styles.buttonsSection}>
        <TouchableOpacity
          style={styles.button}
          onPress={() => navigation.navigate("Water")}
        >
          <Text style={styles.buttonText}>{t("water")}</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.button}>
          <Text style={styles.buttonText}>{t("gas")}</Text>
        </TouchableOpacity>

        <TouchableOpacity style={styles.button}>
          <Text style={styles.buttonText}>{t("electricity")}</Text>
        </TouchableOpacity>
      </SafeAreaView>

      <Text style={styles.changeLanguage}>{t("changeLanguage")}</Text>
      <SafeAreaView style={styles.languages}>
        <TouchableOpacity
          onPress={() => {
            i18n.changeLanguage("ua");
          }}
        >
          <Image
            source={require("../assets/languages/ukraine.png")}
            style={styles.language}
          />
        </TouchableOpacity>
        <TouchableOpacity>
          <Image
            source={require("../assets/languages/russia.png")}
            style={styles.language}
          />
        </TouchableOpacity>

        <TouchableOpacity
          onPress={() => {
            i18n.changeLanguage("en");
          }}
        >
          <Image
            source={require("../assets/languages/united-kingdom.png")}
            style={styles.language}
          />
        </TouchableOpacity>
      </SafeAreaView>
      <TouchableOpacity>
        <Text style={styles.userInfo}>{t("userInfo")}</Text>
      </TouchableOpacity>
      <StatusBar style="auto" />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
  },

  image: {
    resizeMode: "contain",
    //overflow: "visible",
    height: imageHeight,
    marginTop: "10%",
  },

  title: {
    marginTop: "10%",
    textAlign: "center",
    fontFamily: "InterBold",
    fontSize: titleFontSize,
  },

  subtitle: {
    marginTop: "4%",
    textAlign: "center",
    fontFamily: "InterBold",
    fontSize: 12,
    color: "rgba(0, 0, 0, 0.37)",
  },

  buttonsSection: {
    marginTop: "5%",
  },

  button: {
    margin: "1.5%",
    alignItems: "center",
    justifyContent: "center",
    textAlign: "center",
    width: 180,
    height: 50,
    backgroundColor: "#197BDD",
    borderRadius: 50,
    border: 0,
    cursor: "pointer",
  },

  buttonText: {
    fontFamily: "InterBold",
    color: "white",
  },

  changeLanguage: {
    textAlign: "center",
    fontFamily: "InterBold",
    fontSize: 12,
    color: "rgba(0, 0, 0, 0.37)",
    marginTop: "10%",
  },

  languages: {
    flexDirection: "row",
    marginTop: "2%",
    resizeMode: "contain",
  },

  language: {
    alignSelf: "center",
    height: 30,
    width: 30,
    margin: "1%",
  },
  userInfo: {
    marginTop: "5%",
    fontFamily: "InterBold",
    fontSize: 12,
    color: "rgba(0, 0, 0, 0.37)",
  },
});
