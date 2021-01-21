import Axios from '../../apis/Axios';

import jwtDecode from 'jwt-decode';

export const login = async function(username, password){
    let dto = {
        username: username,
        password: password
    };

    try{
        let result = await Axios.post("/users/auth", dto );

        console.log(result.data);
        console.log(jwtDecode(result.data));
        
        window.localStorage.setItem("token", result.data);
        window.localStorage.setItem("username", jwtDecode(result.data).sub);
        window.location.reload();
    }catch(error){
        console.log(error);
        alert("Could not log in.")
    }
};

export const logout = function(){
    window.localStorage.removeItem("token");
    window.location.reload();
}