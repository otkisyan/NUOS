import React, { useRef, useState, useEffect } from "react";
import { Canvas, useFrame } from "@react-three/fiber";
import {
  Grid,
  OrbitControls,
  TransformControls,
  Sky,
  Gltf,
  Edges,
  Icosahedron,
} from "@react-three/drei";
// import { PlaneModel } from "./PlaneModel";
import { useControls } from "leva";

function App() {
  const modes = ["translate", "rotate", "scale"];
  const [transformMode, setTransformMode] = useState(modes[0]);
  const transformControlsRef = useRef();

  useEffect(() => {
    const handleKeyPress = (event) => {
      if (event.key === "c") {
        const currentIndex = modes.indexOf(transformMode);
        const nextIndex = (currentIndex + 1) % modes.length;
        const nextMode = modes[nextIndex];
        setTransformMode(nextMode);
        transformControlsRef.current.setMode(nextMode);
        console.log("Handle switch mode");
      }
    };

    window.addEventListener("keydown", handleKeyPress);
    console.log("Add Event Listener");
    return () => {
      window.removeEventListener("keydown", handleKeyPress);
      console.log("Remove Event Listener");
    };
  }, [transformMode]);

  const { color, mesh, edges, transform } = useControls({
    color: "#6f6f6f",
    mesh: false,
    edges: true,
    transform: {
      value: transformMode,
      options: modes,
      onChange: (newValue) => {
        setTransformMode(newValue);
      },
    },
  });
  return (
    <>
      <Canvas shadows camera={{ position: [5, 2, 5], fov: 25 }}>
        <Sky sunPosition={[100, 20, 100]} />
        <ambientLight intensity={1} />
        <directionalLight position={[5, 2, 5]} intensity={1} castShadow />
        <OrbitControls makeDefault />
        <TransformControls ref={transformControlsRef} mode={transformMode}>
          {/* <PlaneModel>
          </PlaneModel> */}
          <Icosahedron>
            <meshStandardMaterial color={color} visible={mesh} />
            <Edges scale={1.1} threshold={15} color="black" visible={edges} />
          </Icosahedron>
        </TransformControls>
        <Grid args={[10, 10]} />
      </Canvas>
    </>
  );
}

export default App;
