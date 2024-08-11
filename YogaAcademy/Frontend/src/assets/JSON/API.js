import axios from 'axios';

const API_URL = 'http://localhost:3000'

export const getData = async() => await axios.get(`${API_URL}/UserData`);

export const postData = async (username, email, password, userRole) => {
    try {
        const { data: existingData } = await axios.get(`${API_URL}/UserData`);
        const newId = existingData.length ? Math.max(...existingData.map(user => user.id)) + 1 : 1;

        const newUser = {
            id: newId,
            uname: username,
            email,
            password,
            Role: userRole
        };

        await axios.post(`${API_URL}/UserData`, newUser);
    } catch (error) {
        console.error("Error posting data", error);
        throw error;
    }
};



export const showData = async() => {

    const res = await axios.get(`${API_URL}/UserData`);
    return res.data;

}

export const updateData = async (id, updatedUser) => {

        await axios.put(`${API_URL}/UserData/${id}`, updatedUser);

};


export const deleteData = async (id) => {

        await axios.delete(`${API_URL}/UserData/${id}`);

};

export const getUserData = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/UserData/${id}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching user data:', error);
        throw error;
    }
};

// Function to update user plans
export const updateUserPlans = async (id, updatedPlans) => {
    try {
        const response = await axios.put(`${API_URL}/UserData/${id}`, { Plans: updatedPlans });
        return response.data;
    } catch (error) {
        console.error('Error updating user plans:', error);
        throw error;
    }
};

// Function to set a new user password
export const setNewUserPassword = async (id, updatedUser) => {
    try {
        const response = await axios.put(`${API_URL}/UserData/${id}`, updatedUser);
        return response.data;
    } catch (error) {
        console.error('Error updating user password:', error);
        throw error;
    }
};