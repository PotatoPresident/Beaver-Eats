import { useState, useEffect, createContext } from 'react'
import { Canvas } from '@react-three/fiber'
import { OrbitControls } from '@react-three/drei'
import pfp from '../assets/beaverpng.png'

export default function Navbar({ user, state }) {

  const [selectedLocation, setSelectedLocation] = state

  const handleClick = location => {
    setSelectedLocation(location)
  }

  const [locations, setLocations] = useState([])
  useEffect(() => {
    async function getLocations() {
      console.log('[bottom] getting locations ')
      try {
        const res = await fetch('/api/locations', {
          method: 'GET',
          headers: {
            'content-type': 'application/json'
          }
        })
        if(res.ok) setLocations(await res.json())
      } catch (err) { console.log(err) }
    }
    getLocations()
  }, [])

  return (
    <nav className='h-full py-8 px-5 bg-neutral-50 border-r border-neutral-400 font-GeistRegular'>
      <div className='flex flex-col justify-between h-full'>
        <div>
          <div className='flex items-center gap-4'>
            <img src={pfp} className='w-14 h-14'></img>
            <span>Beaver Eats</span>
          </div>
          <div className='flex flex-col py-5'>
            { locations.map((location) => {
              return <div key={location.name} onClick={() => handleClick(location.options[0].group)} className='my-2 cursor-pointer'>{location.name}</div>
            })}
          </div>
        </div>
        { user ? (
            <div className='flex'>
              <img className='w-10 h-10 mr-3 rounded-full' src={user.picture}></img>
              <div className='flex flex-col'>
                <span className='text-sm'>{user.name}</span>
                <span className='text-sm text-neutral-500'>{user.email}</span>
              </div>
            </div>
          ) : (
            <div className='text-sm text-neutral-500'>You aren't signed in</div> 
          )
        }
      </div>
    </nav>
  )
}