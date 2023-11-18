import { useState, useRef, useEffect } from 'react'
import { Loader } from '@googlemaps/js-api-loader'
import Navbar from './components/navbar'
import Location from './components/location'
import Login from './components/login.jsx'

export default function App() {

  const [user, setUser] = useState(null)
  useEffect(() => {
    async function getUser() {
      try {
        const res = await fetch('/api/userinfo', {
          method: 'GET',
          headers: {
            'content-type': 'application/json'
          }
        })
        if(res.ok) setUser(await res.json())
        if(res.status === 401) setUser(null)
      } catch (err) { console.log(err) }
    }
    getUser()
  }, [user])

  const [overflow, setOverflow] = useState('hidden')
  const bottom = useRef(null)
  const map = useRef(null)

  function handleToggle() {
    if (overflow === 'hidden') {
      setOverflow('auto')
      bottom.current.scrollIntoView()
    } else {
      setOverflow('hidden')
      map.current.scrollIntoView()
    }
  }

  return (
    <div className='flex h-screen'>
      { !user && (
        <div className='absolute top-5 right-5 h-16 w-25 z-10'>
          <Login />
        </div>
      )}
      <div className='relative w-1/4 max-w-[263px]'><Navbar user={user}/></div>
      <main className='relative w-full h-full overflow-auto' style={{overflowY: overflow}}>
        <button onClick={handleToggle} className='z-10 rotate-180 absolute top-[90%] left-1/2 transform -translate-x-1/2 -translate-y-1/2'>
          <svg width='14' height='14' viewBox='0 0 14 14' fill='none' xmlns='http://www.w3.org/2000/svg'><path d='M7.58917 0.744078C7.26374 0.418641 6.7361 0.418641 6.41066 0.744078L0.57733 6.57741C0.251893 6.90285 0.251893 7.43049 0.57733 7.75592C0.902767 8.08136 1.4304 8.08136 1.75584 7.75592L6.99992 2.51184L12.244 7.75592C12.5694 8.08136 13.0971 8.08136 13.4225 7.75592C13.7479 7.43049 13.7479 6.90285 13.4225 6.57741L7.58917 0.744078Z' fill='#262626'></path><path d='M13.4225 12.4107L7.58917 6.57741C7.26374 6.25197 6.7361 6.25197 6.41066 6.57741L0.57733 12.4107C0.251893 12.7362 0.251893 13.2638 0.57733 13.5893C0.902767 13.9147 1.4304 13.9147 1.75584 13.5893L6.99992 8.34518L12.244 13.5893C12.5694 13.9147 13.0971 13.9147 13.4225 13.5893C13.7479 13.2638 13.7479 12.7362 13.4225 12.4107Z' fill='#262626'></path></svg>
        </button>
        <div>
          <div ref={map} id='map' className='h-screen w-full'></div>
          <div ref={bottom}>
            <Location />
          </div>
        </div>
      </main>
    </div>
  )
}

const loader = new Loader({
  apiKey: import.meta.env.VITE_GOOGLE_API_KEY,
  version: 'weekly'
})

const CORVALLIS_BOUNDS = {
  north: 44.570405,
  south: 44.555351,
  west: -123.294775,
  east: -123.267350,
}

const CORVALLIS = { lat: 44.564341, lng: -123.280790 }

loader.load().then(async () => {
  const { Map } = await google.maps.importLibrary('maps')
  new Map(document.getElementById('map'), {
    mapId: '54153603699e2b81',
    center: CORVALLIS,
    restriction: {
      latLngBounds: CORVALLIS_BOUNDS,
      strictBounds: false,
    },
    minZoom: 17,
    zoom: 17,
    disableDefaultUI: true,
  })

  //tried to make a marker element according to google maps api but it didn't work
  // const { AdvancedMarkerElement } = await google.maps.importLibrary('marker');
  // const priceTag = document.createElement('div');
  // priceTag.className = 'bg-[#4285F4] text-white text-sm relative px-[15px] py-2.5 rounded-lg';
  // priceTag.textContent = '$2.5M';

  // new AdvancedMarkerElement({
  //   Map,
  //   position: { lat: 44.564341, lng: -123.280790 },
  //   content: priceTag,
  // });
})
