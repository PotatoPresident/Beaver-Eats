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
    <div className='p-4 w-16 h-16'>
      <svg xmlns="http://www.w3.org/2000/svg"><g><path d="M41.233,13.384c0-0.6,0.487-1.086,1.086-1.086c0.601,0,1.086,0.486,1.086,1.086   c0,0.601-0.486,1.086-1.086,1.086C41.719,14.47,41.233,13.984,41.233,13.384 M23.51,16.156c-3.679,1.939-6.937,5.763-7.768,13.151   l-0.117,1.259c-3.2-0.323-7.284-0.027-10.349,0.913c-0.94,0.289-1.834,0.651-2.681,1.154c-4.57,2.684-2.778,9.248,2.824,8.91   c3.784-0.24,8.671-3.032,11.538-5.337l0.195-0.158c0.62,0.704,1.42,1.269,2.402,1.676c-0.187,0.296-0.31,0.568-0.379,0.904   c-0.322,1.539,0.857,3.003,2.413,3.003c5.182,0.137,10.566,0.001,15.77,0.001c0.404,0,0.606-0.314,0.606-0.686   c0.003-0.665,0.037-1.279,0-1.853c-0.006-1.471-1.032-2.892-2.484-3.258c-0.533-0.143-0.748-0.128-1.239-0.128   c-0.226-0.002-0.453,0-0.679,0c1.87-0.638,7.799-6.574,8.179-6.576l3.39-0.014c0.405,0,0.795-0.162,1.084-0.454   c0.566-0.568,0.644-1.892,0.258-2.586c-0.146-0.269-0.338-0.426-0.589-0.56l-1.956-0.941c0.149-0.617,0.367-2.594,1.211-3.058   c1.213-0.621,1.885-1.095,2.635-2.056v0.585c0,0.488,0.396,0.884,0.885,0.884c0.489,0,0.884-0.396,0.884-0.884v-3.183   c0.131-0.193,0.244-0.375,0.32-0.618c0.183-0.586,0.161-2.1,0.054-2.717c-0.094-0.496-0.308-0.944-0.761-1.275   c-0.184-0.128-0.371-0.229-0.573-0.334c-0.653-0.341-1.422-0.655-2.06-0.924c-1.031-0.436-2.064-0.794-3.152-1.059   c-1.03-0.247-2.075-0.413-3.123-0.583c-0.331-0.054-0.513-0.185-0.776-0.369c-0.227-0.161-0.463-0.327-0.735-0.455   C37.52,8.025,36.58,8.419,36.1,9.24c-0.493,0.847-0.315,1.789-0.635,2.62c-0.355,0.821-1.585,1.247-3.242,1.641   C29.845,14.044,26.547,14.531,23.51,16.156z" /></g></svg>
    </div>

    //need to show attribution somewhere in website e.g. in the about page. Beaver by Georgiana Ionescu from <a href="https://thenounproject.com/browse/icons/term/beaver/" target="_blank" title="Beaver Icons">Noun Project</a> (CC BY 3.0)
  )
}