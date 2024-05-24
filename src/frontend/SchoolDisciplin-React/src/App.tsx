import { useState } from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignIn from './pages/Authentification/signin';
import NotFound from './components/NotFound';
import SidebarMenu from './components/Sidebar/sidebar';
import Navbar from './components/Navbar/navbar';
import DashBoard from './pages/dashboard/dashboard';
import Test from './pages/test/test';
import Sanction from './pages/sanctions/sanction';
import Cours from './pages/cours/cours';
import Eleve from './pages/eleves/eleves';
import Convocation from './pages/convocations/convocations';

function App() {
  const [signIn, setSignIn] = useState(false);

  return (
    <BrowserRouter>
      <div className="flex h-screen bg-gray-100">
        {signIn && (
          <aside className="fixed inset-y-0 left-0 bg-gray-800 border-r border-dashed border-white z-40">
            <SidebarMenu />
          </aside>
        )}
        <div className={`flex flex-col flex-1 ${signIn ? "ml-64" : ""}`}>
          {signIn && (
            <header className="fixed inset-x-0 top-0 h-16 bg-gray-800 border-b border-dashed border-white z-30">
              <Navbar />
            </header>
          )}
          <main className={`fixed ml-16 top-16 right-0 bottom-0 left-48 flex-1 ${signIn ? "pt-16" : ""} `}>
            <div className="p-4 relative">
              <Routes>
                <Route path="/" element={<SignIn setSignIn={setSignIn} />} />
                <Route path="/dashboard" element={<DashBoard />} />
                <Route path="/eleves" element={<Eleve />} />
                <Route path="/cours" element={<Cours />} />
                <Route path="/convocations" element={<Convocation />} />
                <Route path="/sanctions" element={<Sanction />} />
                <Route path="*" element={<NotFound />} />
                {/* Other routes */}
              </Routes>
            </div>
          </main>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
