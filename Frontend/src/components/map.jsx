import { Loader } from '@googlemaps/js-api-loader'

export default function Map() {

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
  })

  return <div id='map' className='h-screen w-full'></div>
}