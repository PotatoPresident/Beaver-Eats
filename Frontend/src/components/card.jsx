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
  const [isImagesVisible, setIsImagesVisible] = useState(false)

  const handleClick = section => {

    if (section === 'hours') {
      setIsHoursVisible(!isHoursVisible)
      setIsReviewsVisible(false)
      setIsImagesVisible(false)
    } else if (section === 'reviews') {
      setIsReviewsVisible(!isReviewsVisible)
      setIsHoursVisible(false)
      setIsImagesVisible(false)
    } else if (section === 'images') {
      setIsImagesVisible(!isImagesVisible)
      setIsHoursVisible(false)
      setIsReviewsVisible(false)
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
      <span className='font-GeistBold'>{option.displayName}</span>
      <span className='text-sm text-neutral-500'>{option.description}</span>
      <div className='flex text-sm text-neutral-500 gap-4'>
        <span onClick={() => handleClick('hours')}>Hours</span>
        <span onClick={() => handleClick('reviews')}>Reviews</span>
        <span onClick={() => handleClick('images')}>Images</span>
      </div>

      { isHoursVisible && (
        <div className='flex flex-col text-sm text-neutral-500'>
          <span className='text-sm text-neutral-500'>Hours: {formatHours(option.opens)}-{formatHours(option.closes)}</span>
        </div>
      )}
      { isReviewsVisible && (
        <div className='flex flex-col text-sm text-neutral-500'>
          <span className='text-sm text-neutral-500'>Rating: {option.rating}</span>
          { reviews.map(review => {
            return (
              <div key={review.id}>
                <span className='text-sm text-neutral-500'>{review.text}</span>
              </div>
            )
          })}
          <form method='post' onSubmit={handleSubmit}>
            <input name='rating' defaultValue='Rating'></input>
            <input name='comment' defaultValue='Comment'></input>
            <button type='submit'>Submit Review</button>
          </form>
        </div>
      )}
      { isImagesVisible && (
        <img src={option.images[0]}></img>
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