import { useEffect, useState, useRef, useContext } from 'react'
import { LocationContext } from '../context/context'
import Card from './card'

export default function Bottom() {

  const selectedLocation = useContext(LocationContext)
  const [option, setOption] = useState(null)
  console.log(selectedLocation)

  useEffect(() => {
    async function getOption() {
      try {
        const res = await fetch(`/api/locations/${selectedLocation}`, {
          method: 'GET',
          headers: {
            'content-type': 'application/json'
          }
        })
        if(res.ok) setOption(await res.json())
      } catch (err) { console.log(err) }
    }
    getOption()
  }, [])

  console.log(option)

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
    console.log(scroll)
  }

  return (
    <div className='overflow-x-auto border-t border-neutral-400' ref={sliderRef} onMouseMove={move} onMouseDown={startDragging} onMouseUp={stopDragging} onMouseLeave={stopDragging} >
      <div className='flex h-[400px] min-w-min bg-white justify-betweem items-center gap-24'>
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
        <Card />
      </div>
    </div>
  )
}