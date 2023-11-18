import { useRef } from 'react'
import { useFrame } from '@react-three/fiber'
import { useGLTF } from '@react-three/drei'

export default function Model(props) {

  const { nodes, materials } = useGLTF('/beaver.glb');
  const meshRef = useRef();

  useFrame(() => {
    meshRef.current.position.set(0, 0, 0);
  })

  return (
    <group {...props} dispose={null}>
      <mesh ref={meshRef}
        castShadow
        receiveShadow
        geometry={nodes.Plane.geometry}
        material={materials['Material.001']}
        position={[2.836, 5.753, 0]}
      />
    </group>
  );
}

useGLTF.preload('/beaver.glb');
