import { useRef, useState } from 'react'
import { Canvas, useFrame } from '@react-three/fiber'
import { OrbitControls } from '@react-three/drei'

function Box(props) {

  const ref = useRef()


  return (
    <mesh
      {...props}
      ref={ref}>
      <boxGeometry args={[3, 3, 3]} />
      <meshStandardMaterial color={'orange'} />
    </mesh>
  )
}

export default function Beaver() {
  return (
    <div className='w-16 h-16'>
      <Canvas>
        <ambientLight intensity={Math.PI / 2} />
        <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} decay={0} intensity={Math.PI} />
        <pointLight position={[-10, -10, -10]} decay={0} intensity={Math.PI} />
        <Box position={[0, 0, 0]} />
        <OrbitControls />
      </Canvas>
    </div>
  )
}