import { Canvas } from '@react-three/fiber'
import { OrbitControls } from '@react-three/drei'
import Beaver from './beaver'
import Dropdown from './dropdown'
import pfp from '../assets/bocchi.jpeg'

export default function Navbar() {
  return (
    <nav className='h-full py-8 px-5 bg-neutral-50 border-r border-neutral-400 font-GeistRegular'>
      <div className='flex flex-col justify-between h-full'>
        <div>
          <div className='w-14 h-14'>
            <Canvas>
              <ambientLight intensity={Math.PI / 2} />
              <spotLight position={[10, 10, 10]} angle={0.15} penumbra={1} decay={0} intensity={Math.PI} />
              <pointLight position={[-10, -10, -10]} decay={0} intensity={Math.PI} />
              <Beaver />
              <OrbitControls />
            </Canvas>
          </div>
          <div className='flex flex-col'>
            <span>Locations</span>
            <Dropdown location={'West'} options={['Serranos', 'Ring of Fire', 'Sandwhich']}/>
            <Dropdown location={'McNary'} options={['Garbage']}/>
            <Dropdown location={'Arnold'} options={['Pizza', 'Rice Bowl']}/>
            <Dropdown location={'Memorial Union'} options={['Panda Express', 'Rocket Mortgage']}/>
            <Dropdown location={'Cafes & Markets'} options={['Trader Bings', 'EBGBs']}/>
          </div>
        </div>
        <div className='flex'>
          <img className='w-10 h-10 mr-3' src={pfp}></img>
          <div className='flex flex-col'>
            <span>Name</span>
            <span>example@email.com</span>
          </div>
        </div>
      </div>
    </nav>
  )
}