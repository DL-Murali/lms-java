import React from "react";
import "./Login.css";
import { Link } from "react-router-dom";
const Login = () => {
  return (
    <div>
      <div className="bg-dark d-flex justify-content-center align-items-center">
        <div className="bg-white rounded-3">
          <img
            className="py-5"
            src="https://digital-lync.konalms.com/assets/logo.ab024049.png"
            alt="Logo"
            width={"200px"}
          />
          <h2 className="fw-normal pb-2">Welcome</h2>
          <p>Log in to Digial Lync to continue to Digital Lync LMS.</p>
          <div className="px-3 py-2 d-flex flex-column gap-3 ">
            <input
              type="text"
              placeholder="Email adress"
              required
              className=" p-3 col-12   rounded-1"
            />
            <input
              type="text"
              placeholder="Password"
              required
              className=" p-3 col-12  rounded-1"
            />
            <Link to="/" className="text-decoration-none">
              <p className="text-start fw-bold">Forgot password?</p>
            </Link>
            <Link to="/" className="my-2">
              <button className="btn btn-primary col-12 p-3">Continue</button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
