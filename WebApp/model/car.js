// models/Car.js
const mongoose = require('mongoose');

const carSchema = new mongoose.Schema({
    carName: { type: String, required: true },
    carPlaque: { type: String, required: true },
    deliveredYear: { type: Number, required: true },
    isElectric: { type: Boolean, default: false },
});

const Car = mongoose.model('Car', carSchema);
module.exports = Car;