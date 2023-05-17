require('dotenv').config()
const mongoose = require('mongoose');

console.log(process.env.MONGO_USER)

mongoose.set("strictQuery", false);
mongoose.connect(`mongodb+srv://${process.env.MONGO_USER}:${process.env.MONGO_PASSWORD}@${process.env.MONGO_CLUSTER}.gooxams.mongodb.net/${process.env.MONGO_DATABASE}`)
.then(() => {
    console.log('connected');
})
.catch((err) => {
    console.log(err);
})

// mongodb+srv://tester:<password>@test.gooxams.mongodb.net/