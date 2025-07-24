import { Link } from "react-router";

function NotFound() {

    return(
        <>
        <div style={{textAlign: 'center'}}>
        <h1>NOT FOUND</h1>
        <p>Route does NOT exist</p>
        <Link to="/">Back to Home</Link>
        </div>
        </>

    );
};


export default NotFound;