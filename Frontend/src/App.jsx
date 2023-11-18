import { Loader } from '@googlemaps/js-api-loader'
import Navbar from './components/navbar'

export default function App() {

  return (
    <div className='flex'> 
      <Navbar/>
      <div id='map' className='flex w-full' />
    </div>
  )
}

const loader = new Loader({
  apiKey: import.meta.env.VITE_GOOGLE_API_KEY,
  version: 'weekly'
})
loader.load().then(async() => {
  const { Map } = await google.maps.importLibrary('maps')

  new Map(document.getElementById('map'), {
    center: { lat: 44.564341, lng: -123.280790 },
    zoom: 17,
  });
})
