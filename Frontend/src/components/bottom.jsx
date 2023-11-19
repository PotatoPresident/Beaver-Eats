import { useEffect, useState, useRef } from 'react'
import Card from './card'

export default function Bottom({ state }) {

  const [selectedLocation, setSelectedLocation] = state
  const [options, setOptions] = useState(null)

  useEffect(() => {
    async function getOptions() {
      console.log('[bottom] getting options')
      try {
        const res = await fetch(`/api/locations/${selectedLocation}`, {
          method: 'GET',
          headers: {
            'content-type': 'application/json'
          }
        })
        if(res.ok) setOptions(await res.json())
      } catch (err) { console.log(err) }
    }
    getOptions()

    return () => {
      console.log('Cleanup function - Component unmounted');
    }
  }, [selectedLocation])

  const [mouseDown, setMouseDown] = useState(false)
  const [startX, setStartX] = useState(0)
  const [scrollLeft, setScrollLeft] = useState(0)

  const sliderRef = useRef(null)

  const startDragging = (e) => {
    setMouseDown(true)
    setStartX(e.pageX - sliderRef.current.offsetLeft)
    setScrollLeft(sliderRef.current.scrollLeft)
  }

  const stopDragging = () => {
    setMouseDown(false)
  }

  const move = (e) => {
    e.preventDefault()
    if (!mouseDown) return
    const x = e.pageX - sliderRef.current.offsetLeft
    const scroll = x - startX
    sliderRef.current.scrollLeft = scrollLeft - scroll
  }

  return (
    <div className='overflow-x-auto border-t border-neutral-400 px-24' ref={sliderRef} onMouseMove={move} onMouseDown={startDragging} onMouseUp={stopDragging} onMouseLeave={stopDragging} >
      <div className='flex h-[400px] min-w-min bg-white justify-between items-center gap-24'>
        { options && options.map(option => {
          return <Card key={option.name} option={option} />
        })}
      </div>
    </div>
  )
}