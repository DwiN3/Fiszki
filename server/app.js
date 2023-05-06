const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('./database/mongoose');
const userRoutes = require('./routes/user');
const errorHandler = require('./middleware/error');

const app = express();

app.use(bodyParser.urlencoded({ extended : false }));
app.use(bodyParser.json());

app.use('/api/users', userRoutes);
app.use(errorHandler);

app.listen(8080);