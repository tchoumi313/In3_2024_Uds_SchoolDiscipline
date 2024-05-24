import user from '../../images/user/user-00.png'

const Navbar: React.FC = () => {

    return (
        <div className="fixed left-60 top-0 right-0 h-16 shadow-xl">
            <nav className="bg-slate-200 dark:bg-slate-800">
                <div className="ml-auto mr-4 max-w-7xl px-4 sm:px-6 lg:px-8">
                    <div className="flex h-16 items-center justify-between">
                        <div className="flex items-center">
                            {/* <div className="flex-shrink-0">
                                <img className="h-8 w-8" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500" alt="Your Company" />
                            </div> */}
                            <div className="hidden md:block">
                                <div className="ml-10 flex items-baseline space-x-4">
                                    {/* <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" --> */}
                                    <input placeholder="Search" type="search" name="search" id="" className="dark:bg-gray-800 bg-slate-200 px-3 py-2 border-b-2 border-x-none w-52 border-gray-400 dark:border-gray-600 text-sm leading-5 text-white focus:ring-0"/>
                                </div>
                            </div>
                        </div>
                        <div className="hidden md:block">
                            <div className="ml-4 flex items-center md:ml-6">
                                <button type="button" className="relative rounded-full bg-slate-300 dark:bg-gray-800 p-1 text-gray-500 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                                    <span className="absolute -inset-1.5"></span>
                                    <span className="sr-only">View notifications</span>
                                    <svg className="h-6 w-6 hover:scale-105" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                                        <path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0" />
                                    </svg>
                                </button>

                                {/* <!-- Profile dropdown --> */}
                                <div className="relative ml-3">
                                    <div>
                                        <button type="button" className="relative flex max-w-xs items-center rounded-full bg-slate-300 dark:bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
                                            <span className="absolute -inset-1.5"></span>
                                            <span className="sr-only">Open user menu</span>
                                            <img className="h-8 w-8 rounded-full hover:scale-105" src={user} alt="img" />
                                        </button>
                                    </div>
                                    <div className="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none" role="menu" aria-orientation="vertical" aria-labelledby="user-menu-button">
                                        {/* <!-- Active: "bg-gray-100", Not Active: "" --> */}
                                        <a href="#" className="block px-4 py-2 text-sm text-gray-700 hover:bg-slate-300 hover:scale-105 mx-2 rounded" role="menuitem" id="user-menu-item-0">Your Profile</a>
                                        <a href="#" className="block px-4 py-2 text-sm text-gray-700 hover:bg-slate-300 hover:scale-105 mx-2 rounded" role="menuitem" id="user-menu-item-1">Settings</a>
                                        <a href="#" className="block px-4 py-2 text-sm text-gray-700 hover:bg-slate-300 hover:scale-105 mx-2 rounded" role="menuitem" id="user-menu-item-2">Sign out</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        {/* <div className="-mr-2 flex md:hidden">
                            <button type="button" className="relative inline-flex items-center justify-center rounded-md bg-gray-800 p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" aria-controls="mobile-menu" aria-expanded="false">
                                <span className="absolute -inset-0.5"></span>
                                <span className="sr-only">Open main menu</span>
                                <svg className="block h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
                                </svg>
                                <svg className="hidden h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                                </svg>
                            </button>
                        </div> */}
                    </div>
                </div>
            </nav>
        </div>
    )
}

export default Navbar;