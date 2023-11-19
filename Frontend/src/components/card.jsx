export default function Cards({ option }) {
  return (
    <div className='flex flex-col justify-center items-center w-[400px] min-w-[400px] h-[300px] bg-neutral-100 border border-neutral-400 rounded-md shadow-xl'>
      <img className='rounded-sm' src={option.images[0]}></img>
      <span className='font-GeistBold'>{option.displayName}</span>
      <span className='text-sm text-neutral-500'>{option.description}</span>
      <span className='text-sm text-neutral-500'>Hours: {option.opens}-{option.closes}</span>
      <span className='text-sm text-neutral-500'>Rating: {option.rating}</span>
    </div>
  )
}