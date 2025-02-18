import {Link} from "react-router";

const Home = () => {
    return (
        <div>
            Home
            <Link to="/problems">Go To Problems</Link>
        </div>
    );
};

export default Home;