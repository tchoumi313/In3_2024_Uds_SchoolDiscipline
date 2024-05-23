// import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../../images/icon/schoolDisciplin.png'
import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';

const SidebarMenu = () => {
    return (
        <div>
            <Sidebar className='w-48  fixed top-0 left-0 bottom-0 h-full sm:w-3/12 z-50'>
                <Menu className="bg-slate-900 h-screen text-left px-2 text-white z-50">
                    <span className=" mb-3 flex items-center justify-center">
                        <img src={logo} alt="logo" className="w-48 h-20 my-4" />
                        <hr className='border border-slate-50'/>
                    </span>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/dashboard" />} icon={<DashboardIcon />}>
                        Dashboard
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/professeurs" />} icon={<ProfessorsIcon />}>
                        Professeurs
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/personnels" />} icon={<StaffIcon />}>
                        Personnels
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/cours" />} icon={<CoursesIcon />}>
                        Cours
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/convocations" />} icon={<ConvocationsIcon />}>
                        Convocations
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/sanctions" />} icon={<SanctionsIcon />}>
                        Sanctions
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/conseil-discipline" />} icon={<DisciplineCouncilIcon />}>
                        Conseil Discipline
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/fautes" />} icon={<FaultsIcon />}>
                        Fautes
                    </MenuItem>
                    <MenuItem className='hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg' component={<Link to="/classes" />} icon={<ClassesIcon />}>
                        Classes
                    </MenuItem>
                </Menu>
            </Sidebar>
        </div>
    );
};

// SVG Icons for each menu item
const DashboardIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 3h18M3 7h18M3 11h18M3 15h18M3 19h18"></path>
    </svg>
);

const ProfessorsIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/5000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 12h14M12 5v14M5 5l14 14M5 19l14-14"></path>
    </svg>
);

const StaffIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/9000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 12h14M12 5v14M5 5l14 14M5 19l14-14"></path>
    </svg>
);

const CoursesIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
    </svg>
);

const ConvocationsIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 3h18M3 7h18M3 11h18M3 15h18M3 19h18"></path>
    </svg>
);

const SanctionsIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
    </svg>
);

const DisciplineCouncilIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
    </svg>
);

const FaultsIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 3h18M3 7h18M3 11h18M3 15h18M3 19h18"></path>
    </svg>
);

const ClassesIcon = () => (
    <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
    </svg>
);

export default SidebarMenu;
