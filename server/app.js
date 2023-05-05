const express = require('express');
const mongoose = require('./database/mongoose');
const errorHandler = require('./middleware/error');

const app = express();

app.use(errorHandler)

app.listen(8080);