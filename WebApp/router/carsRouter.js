// routes/cars.js
const express = require('express');
const Car = require('../model/car');
const router = express.Router();

// Create a new car
// Create a new car

router.post('/', async (req, res) => {

    console.log('Received request to create a new car:', req.body); // Log the incoming request

    const { carName, carPlaque, deliveredYear, isElectric } = req.body;

    try {

        const newCar = new Car({ carName, carPlaque, deliveredYear, isElectric });

        await newCar.save();

        res.status(201).json(newCar);

    } catch (error) {

        console.error('Error saving car:', error.message); // Log the error

        res.status(400).json({ message: error.message });

    }

});

// Read all cars
router.get('/display', async (req, res) => {
    try {
        const cars = await Car.find();
        res.json(cars);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Update a car by ID
router.put('/:id', async (req, res) => {
    try {
        const updatedCar = await Car.findByIdAndUpdate(req.params.id, req.body, { new: true });
        if (!updatedCar) return res.status(404).json({ message: 'Car not found' });
        res.json(updatedCar);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Delete a car by ID
router.delete('/:id', async (req, res) => {
    try {
        const deletedCar = await Car.findByIdAndDelete(req.params.id);
        if (!deletedCar) return res.status(404).json({ message: 'Car not found' });
        res.json({ message: 'Car deleted successfully' });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;