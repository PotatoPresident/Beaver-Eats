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
  apiKey: "AIzaSyCs4CKMrfMTvNVsxSHtf0LB6F2RTUjBW7M", //import.meta.env.VITE_GOOGLE_API_KEY,
  version: 'weekly'
})
loader.load().then(async() => {
  const { Map } = await google.maps.importLibrary('maps')

  new Map(document.getElementById('map'), {
    center: { lat: -34.397, lng: 150.644 },
    zoom: 8,
  });
})
