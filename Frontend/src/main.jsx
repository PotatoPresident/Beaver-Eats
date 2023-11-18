import * as React from 'react'
import * as ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import App from './App'
import Error from './routes/error'
import About from './routes/about'
import './index.css'

const router = createBrowserRouter([
  { path: '/', element: <App />, errorElement: <Error /> },
  { path: '/about', element: <About />, errorElement: <Error /> }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);


