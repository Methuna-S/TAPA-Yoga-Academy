import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import '../assets/css/Buy.css';

const courses = [
  { id: 'course1', name: 'Yoga', price: 100 },
  { id: 'course2', name: 'Pilates', price: 150 },
  { id: 'course3', name: 'Crossfit', price: 200 }
];

const Buy = () => {
  const { id } = useParams();
  const [userInfo, setUserInfo] = useState({
    name: '',
    age: '',
    gender: '',
    phoneNumber: '',
    email: ''
  });

  const [upi, setUpi] = useState('');
  const [selectedTrainer, setSelectedTrainer] = useState('');
  const [selectedCourse, setSelectedCourse] = useState(null);

  const trainers = [
    { id: 1, name: 'John Doe', rating: 4.5 },
    { id: 2, name: 'Jane Smith', rating: 4.0 },
    { id: 3, name: 'Alex Johnson', rating: 4.7 }
  ];

  useEffect(() => {
    const course = courses.find(course => course.id === id);
    setSelectedCourse(course);
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserInfo({ ...userInfo, [name]: value });
  };

  const handleBuy = () => {
    if (!selectedCourse) return;
    console.log('User Info:', userInfo);
    console.log('UPI Method:', upi);
    console.log('Selected Trainer:', selectedTrainer);
    console.log('Selected Course:', selectedCourse.name);
    console.log('Payment Amount:', selectedCourse.price);
    // Handle the buy action, e.g., send data to the server
  };

  return (
    <div className="container">
      <div className="form-container">
        <h1>Buy Page for Plan ID: {id}</h1>
        {selectedCourse && <h2>Course: {selectedCourse.name} - ${selectedCourse.price}</h2>}
        <hr />
        <div>
          <h2>User Information</h2>
          <form>
            <label>
              Name:
              <input type="text" name="name" value={userInfo.name} onChange={handleChange} placeholder="Your Name" required />
            </label>
            <label>
              Age:
              <input type="number" name="age" value={userInfo.age} onChange={handleChange} placeholder="Your Age" required />
            </label>
            <label>
              Gender:
              <select name="gender" value={userInfo.gender} onChange={handleChange} required>
                <option value="">Select</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
              </select>
            </label>
            <label>
              Phone Number:
              <input type="tel" name="phoneNumber" value={userInfo.phoneNumber} onChange={handleChange} placeholder="03077684467" required />
            </label>
            <label>
              Email:
              <input type="email" name="email" value={userInfo.email} onChange={handleChange} placeholder="xyz@gmail.com" required />
            </label>
          </form>
        </div>

        <div>
          <h2>Payment Details</h2>
          <label>
            Amount:
            <input type="text" placeholder="Enter the amount:" required />
          </label>
          <label>
            UPI Method:
            <input type="text" value={upi} onChange={(e) => setUpi(e.target.value)} placeholder="Enter UPI ID" required />
          </label>
        </div>

        <div>
          <h2>Choose Personal Trainer</h2>
          <select value={selectedTrainer} onChange={(e) => setSelectedTrainer(e.target.value)} required>
            <option value="">Select Trainer</option>
            {trainers.map(trainer => (
              <option key={trainer.id} value={trainer.name}>
                {trainer.name} - {trainer.rating} ★
              </option>
            ))}
          </select>
        </div>

        <button onClick={handleBuy}>Buy Now</button>
      </div>
    </div>
  );
}

export default Buy;
