import { useState } from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignIn from './pages/Authentification/signin';
import NotFound from './components/NotFound';
import SidebarMenu from './components/Sidebar/sidebar';
import Navbar from './components/Navbar/navbar';
import DashBoard from './pages/dashboard/dashboard';

function App() {
  const [signIn, setSignIn] = useState(false);

  return (
    <BrowserRouter>
      {signIn && (
        <div className="fixed left-0 top-0 bottom-0 w-48 z-40">
          <SidebarMenu />
        </div>
      )}
      <div className="relative">
        {signIn && (
          <div className="flex shadow justify-between p-4 ml-8 items-center fixed left-40 z-30 right-0 ">
            <Navbar />
          </div>
        )}
        <div className="relative right-0 left-36 bottom-0 top-16 z-10 ">
          <Routes>
            <Route path="/" element={<SignIn setSignIn={setSignIn} />} />
            <Route path="/dashboard" element={<DashBoard />} />
            <Route path="*" element={<NotFound />} />
            {/* Autres routes */}
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
