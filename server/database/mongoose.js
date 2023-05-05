const mongoose = require('mongoose');

mongoose.set("strictQuery", false);
mongoose.connect('mongodb+srv://tester:duchy228@test.gooxams.mongodb.net/fiszki')
.then(() => {
    console.log('connected');
})
.catch((err) => {
    console.log(err);
})