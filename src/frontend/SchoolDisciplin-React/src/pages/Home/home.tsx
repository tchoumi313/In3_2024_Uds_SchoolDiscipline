import NotFound from "../../components/NotFound";
import SidebarMenu from "../../components/Sidebar/sidebar"
import Navbar from './../../components/Navbar/navbar';

const Home: React.FC = () => {

    return (
        <div className="container">
            <div className="fixed left-0 right-0 bottom-0">
                <SidebarMenu/>
            </div>
            <div>
                <div>
                <Navbar/>
                </div>
                <div>
                    <NotFound/>
                    {/* <app-routlet></app-routlet> */}
                </div>
            </div>
        </div>
    )

}

export default Home