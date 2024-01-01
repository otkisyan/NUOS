import HomeScreen from "./screens/HomeScreen";
import Water from "./screens/Water";
import WaterEntries from "./screens/WaterEntries";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useFonts } from "expo-font";
import "./languages/i18n";

const Stack = createNativeStackNavigator();

export default function App() {
  const [loaded] = useFonts({
    InterBold: require("./fonts/Inter-Bold.ttf"),
    InterMedium: require("./fonts/Inter-Medium.ttf"),
  });

  if (!loaded) {
    return null;
  }

  return (
    <NavigationContainer>
      <Stack.Navigator
        screenOptions={{
          headerShown: false,
        }}
      >
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Water" component={Water} />
        <Stack.Screen name="WaterEntries" component={WaterEntries} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
