export default function AuthButtons() {
  return (
    <div className="absolute z-10">
      <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 m-4 rounded">
        <a href={`/api/login?redirectUrl=${window.location.href}`}>Login</a>
      </button>
    </div>
    
  )
}