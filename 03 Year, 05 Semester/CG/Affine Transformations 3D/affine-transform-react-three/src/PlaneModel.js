import React, { useRef } from "react";
import { useGLTF } from "@react-three/drei";

export function PlaneModel(props) {
  const { nodes, materials } = useGLTF("/plane.glb");
  return (
    <group {...props} dispose={null}>
      <group position={[0, 0.065, 0.141]} rotation={[-1.658, 0, 0]}>
        <group rotation={[Math.PI / 2, 0, 0]}>
          <group
            position={[0.016, 1.037, -0.418]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          >
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_10.geometry}
              material={materials["Wwhitepaint.001"]}
            />
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_11.geometry}
              material={materials["Wwhitepaint.001"]}
            />
          </group>
          <group
            position={[0.01, 1.201, -0.839]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          >
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_47.geometry}
              material={materials.carpaint}
            />
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_48.geometry}
              material={materials.carpaint}
            />
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_49.geometry}
              material={materials.carpaint}
            />
          </group>
          <group
            position={[0.01, 1.201, -0.839]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          >
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_53.geometry}
              material={materials["body_texture.001"]}
            />
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_54.geometry}
              material={materials["body_texture.001"]}
            />
            <mesh
              castShadow
              receiveShadow
              geometry={nodes.Object_55.geometry}
              material={materials["body_texture.001"]}
            />
          </group>
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_4.geometry}
            material={materials["chrome.001"]}
            position={[0.01, 1.327, -2.909]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_6.geometry}
            material={materials["tire.001"]}
            position={[0.01, 0.636, -0.438]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_8.geometry}
            material={materials["body_texture.001"]}
            position={[0.011, 0.399, -0.07]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_13.geometry}
            material={materials["plastic_rough_grey_plus.001"]}
            position={[0.01, 0.314, -0.315]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_15.geometry}
            material={materials["chrome.001"]}
            position={[-0.024, 0.377, 1.456]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_17.geometry}
            material={materials["tire.001"]}
            position={[0.008, 0.195, 0.712]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_19.geometry}
            material={materials["chrome.001"]}
            position={[0.01, 0.409, -0.05]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_21.geometry}
            material={materials["metal_yellow.001"]}
            position={[0.024, 0.176, 3.404]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_23.geometry}
            material={materials["body_texture.001"]}
            position={[0.01, 0.622, -0.109]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_25.geometry}
            material={materials["glass_clear.001"]}
            position={[0.007, 1.184, 0.003]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_27.geometry}
            material={materials["chrome.001"]}
            position={[0.014, 1.464, -3.464]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_29.geometry}
            material={materials["light_white.001"]}
            position={[0.046, 1.328, -1.552]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_31.geometry}
            material={materials["metal_rough_plus.001"]}
            position={[-0.087, 1, -0.21]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_33.geometry}
            material={materials["glass_clear.001"]}
            position={[0.011, 1.557, 1.382]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_35.geometry}
            material={materials["chrome.001"]}
            position={[0.059, 1.321, -0.799]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_37.geometry}
            material={materials["plastic_rough_black.001"]}
            position={[0.029, 1.69, -1.128]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_39.geometry}
            material={materials["plastic_rough_black_plus.001"]}
            position={[-0.026, 1.157, 1.076]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_41.geometry}
            material={materials["board.001"]}
            position={[0.011, 1.344, 2.6]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_43.geometry}
            material={materials["metal_rough.001"]}
            position={[0.011, 0.825, 0.687]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_45.geometry}
            material={materials["chrome.001"]}
            position={[0, 1.373, -1.684]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
          <mesh
            castShadow
            receiveShadow
            geometry={nodes.Object_51.geometry}
            material={materials["plastic_rough_black.001"]}
            position={[0.011, 1.556, 1.382]}
            rotation={[0, Math.PI / 2, 0]}
            scale={0.001}
          />
        </group>
      </group>
    </group>
  );
}

useGLTF.preload("/plane.glb");
