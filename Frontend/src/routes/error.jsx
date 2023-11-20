import { useRouteError } from 'react-router-dom'

export default function Error() {
  const error = useRouteError()
  console.error(error)

  return (
    <div className='w-screen h-screen flex items-center justify-center'>
      <p>
        <span className='text-4xl font-GeistBlack'>{error.statusText || error.message}</span>
      </p>
    </div>
  );
}