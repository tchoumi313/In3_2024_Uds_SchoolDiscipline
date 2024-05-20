import { useState } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignIn from './pages/Authentification/signin'
import NotFound from './components/NotFound';
import Test from './pages/Authentification/signup';
function App() {
  const [] = useState(0)

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<SignIn />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<Test />} />
        <Route path="*" element={<NotFound />} />
        {/* Autres routes */}
      </Routes>
    </BrowserRouter>
  )
}

export default App
