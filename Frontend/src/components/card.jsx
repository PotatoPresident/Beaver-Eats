import { useState, useEffect } from 'react'

export default function Cards({ option }) {

  const [reviews, setReviews] = useState([])

  useEffect(() => {
    async function getReviews() {
      console.log('[card] getting reviews')
      try {
        const res = await fetch(`/api/reviews/${option.id}`, {
          method: 'GET',
          headers: {
            'content-type': 'application/json'
          }
        })
        if(res.ok) setReviews(await res.json())
      } catch (err) { console.log(err) }
    }
    getReviews()
  }, [])

  const [isHoursVisible, setIsHoursVisible] = useState(false)
  const [isReviewsVisible, setIsReviewsVisible] = useState(false)
  const [isOverviewVisible, setOverview] = useState(false)

  const handleClick = section => {

    if (section === 'overview') {
      setOverview(!isOverviewVisible)
      setIsHoursVisible(false)
      setIsReviewsVisible(false)
    } else if (section === 'hours') {
      setIsHoursVisible(!isHoursVisible)
      setIsReviewsVisible(false)
      setOverview(false)
    } else if (section === 'reviews') {
      setIsReviewsVisible(!isReviewsVisible)
      setIsHoursVisible(false)
      setOverview(false)
    }
  }

  const handleSubmit = async e => {
    e.preventDefault()
    const comment = e.target
    const formData = new FormData(comment)
    formData.append('locationId', option.id)
    await fetch('/api/reviews', {
      method: 'POST',
      body: formData,
    })
  }

  return (
    <div className='flex flex-col justify-center items-center w-[400px] min-w-[400px] h-[300px] bg-neutral-100 border border-neutral-400 rounded-md shadow-xl'>
      <img className='rounded w-auto h-24' src={option.images[0]}></img>
      <span className='font-GeistBold pt-2'>{option.displayName}</span>
      
      <div className='cursor-pointer flex text-sm text-neutral-500 gap-4 pt-2'>
        <div className='relative items-baseline hover:text-neutral-900 w-16 h-5 rounded'>
          <span className='flex justify-center items-center' onClick={() => handleClick('overview')}>Overview</span>
        </div>
        <div className='relative items-baseline hover:text-neutral-900 w-16 h-5 rounded'>
          <span className='flex justify-center items-center' onClick={() => handleClick('hours')}>Hours</span>
        </div>
        <div className='relative items-baseline hover:text-neutral-900 w-16 h-5 rounded'>
          <span className='flex justify-center items-center' onClick={() => handleClick('reviews')}>Reviews</span>
        </div>
      </div>
      <hr className='w-2/3 border-neutral-400 pb-2'></hr>

      { isOverviewVisible && (
        <div className='h-20 overflow-y-auto px-4'>
          <span className='overflow-y-clip text-sm text-neutral-500'>{option.description}</span>
        </div>
      )}
      { isHoursVisible && (
        <div className='flex flex-col text-sm text-neutral-500'>
          <span className='text-sm text-neutral-500'>Hours: {formatHours(option.opens)}-{formatHours(option.closes)}</span>
        </div>
      )}
      { isReviewsVisible && (
        <div className='flex flex-col text-sm text-neutral-500'>
          <span className='text-sm text-neutral-500'>{option.rating.toFixed(1)} Stars</span>
          <div className='py-2'>
          <div className='relative max-h-[50px] flex flex-col overflow-y-auto'>
            { reviews.map(review => {
              return (
                <span className='text-sm text-neutral-500'>{review.review}</span>
              )
            })}
          </div>
          </div>
          <form method='post' onSubmit={handleSubmit}>
            <input
              className='rounded min-w-[50px] border border-neutral-400 p-1 outline-none'
              name='rating'
              placeholder='Rating'
              type='number'
              min={1}
              max={5}
              step={0.1}
              required
            />
            <input
              className='rounded min-w-[80px] border border-neutral-400 p-1 outline-none'
              name='comment'
              placeholder='Comment'
              style={{margin:'0 15px'}}
            />
            
            <button
            type='submit'>Submit Review
            
            </button>
          </form>
        </div>
      )}
    </div>
  )
}

function formatHours(hours) {
    //it is pm if hours from 12 onwards
    const suffix = (hours >= 12)? 'pm' : 'am';

    //only -12 from hours if it is greater than 12 (if not back at mid night)
    hours = (hours > 12)? hours -12 : hours;

    //if 00 then it is 12 am
    hours = (hours == '00')? 12 : hours;

    return `${hours}${suffix}`
}