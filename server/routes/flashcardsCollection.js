const express = require('express');
const flashcardsCollectionController = require('../controllers/flashcardsCollection');
const isAuth = require('../middleware/auth');

const router = express.Router();

router.get('/', isAuth, flashcardsCollectionController.getCollections);

module.exports = router;