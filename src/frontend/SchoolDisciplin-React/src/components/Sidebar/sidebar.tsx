import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import logo from '../../images/icon/schoolDisciplin.png';
import { Sidebar, Menu, MenuItem } from 'react-pro-sidebar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faBars,
    faTachometerAlt,
    faUserGraduate,
    faUserTie,
    faBook,
    faEnvelopeOpenText,
    faBan,
    faGavel,
    faExclamationTriangle,
    faChalkboardTeacher,
} from '@fortawesome/free-solid-svg-icons';

const SidebarMenu = () => {
    const [isSidebarOpen, setIsSidebarOpen] = useState(false);

    const toggleSidebar = () => {
        setIsSidebarOpen(!isSidebarOpen);
    };

    return (
        <div>
            <button
                className="sm:hidden text-white p-4"
                onClick={toggleSidebar}
            >
                <FontAwesomeIcon icon={faBars} size="2x" />
            </button>
            <Sidebar
                className={`fixed top-0 left-0 bottom-0 h-full transition-transform transform ${
                    isSidebarOpen ? 'translate-x-0' : '-translate-x-full'
                } sm:translate-x-0 sm:w-3/12 z-50 w-48`}
            >
                <Menu className="bg-slate-900 h-screen text-left px-2 text-white z-50">
                    <span className="mb-3 flex items-center justify-center">
                        <img src={logo} alt="logo" className="w-48 h-20 my-4" />
                        <hr className="border border-slate-50" />
                    </span>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/dashboard" />}
                        icon={<FontAwesomeIcon icon={faTachometerAlt} />}
                    >
                        Dashboard
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/eleves" />}
                        icon={<FontAwesomeIcon icon={faUserGraduate} />}
                    >
                        Eleves
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/personnels" />}
                        icon={<FontAwesomeIcon icon={faUserTie} />}
                    >
                        Personnels
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/cours" />}
                        icon={<FontAwesomeIcon icon={faBook} />}
                    >
                        Cours
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/convocations" />}
                        icon={<FontAwesomeIcon icon={faEnvelopeOpenText} />}
                    >
                        Convocations
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/sanctions" />}
                        icon={<FontAwesomeIcon icon={faBan} />}
                    >
                        Sanctions
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/conseil-discipline" />}
                        icon={<FontAwesomeIcon icon={faGavel} />}
                    >
                        Conseil Discipline
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/fautes" />}
                        icon={<FontAwesomeIcon icon={faExclamationTriangle} />}
                    >
                        Fautes
                    </MenuItem>
                    <MenuItem
                        className="hover:bg-slate-500 hover:text-gray-900 hover:rounded-xl rounded-lg"
                        component={<Link to="/classes" />}
                        icon={<FontAwesomeIcon icon={faChalkboardTeacher} />}
                    >
                        Classes
                    </MenuItem>
                </Menu>
            </Sidebar>
        </div>
    );
};

export default SidebarMenu;
