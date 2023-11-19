import { Loader } from '@googlemaps/js-api-loader'

export default function Map({ state }) {
  const [selectedLocation, setSelectedLocation] = state;
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
    const locations = await (await fetch('/api/locations', {
      method: 'GET',
      headers: {
        'content-type': 'application/json'
      }
    })).json()
    const { Map } = await google.maps.importLibrary('maps')
    const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");
    const map = new Map(document.getElementById('map'), {
      mapId: '54153603699e2b81',
      center: CORVALLIS,
      restriction: {
        latLngBounds: CORVALLIS_BOUNDS,
        strictBounds: false,
      },
      minZoom: 16.75,
      zoom: 16.75,
      disableDefaultUI: true,
    })

    locations.map((group) => {
      group.options.map((option) => {
        const marker = new AdvancedMarkerElement({
          map,
          position: {lat: option.lat, lng: option.lng},
          title: option.displayName,
        });
        marker.addListener("click", () => {
          setSelectedLocation(option.group)
          const clickedMarkerCoords = {lat: option.lat, lng: option.lng}
          map.panTo(clickedMarkerCoords)
          console.log('map paned to ' + clickedMarkerCoords)
       });
if (selectedLocation === 'McNary') {
  map.panTo({lat:44.56412339072111 , lng:-123.27237759512379 })
  map.setZoom(18)
}
else if(selectedLocation === 'Arnold') {
  map.panTo({lat:44.56045267241557 , lng:-123.27759333004018 })
  map.setZoom(18)
}
else if(selectedLocation === 'West') {
  map.panTo({lat:44.56390497552497,  lng:-123.2835802319097})
  map.setZoom(18)
}

else if(selectedLocation === 'MU') {
  map.panTo({lat:44.5649206643527,  lng: -123.2789069030742 })
  map.setZoom(18)
}

else if(selectedLocation === 'Other') {
  map.panTo({lat:44.5649206643527,  lng: -123.2789069030742 })
  map.setZoom(16.75)
} 
      })
    })
  })

  return <div id='map' className='h-screen w-full'></div>
}