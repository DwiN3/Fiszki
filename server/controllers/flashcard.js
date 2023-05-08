const Flashcard = require('../models/flashcard');
const FlashcardsCollection = require('../models/flashcardsCollection');
const { validationResult } = require('express-validator');
const jwt = require('jsonwebtoken');


exports.addFlashCard = async(req, res, next) => {

    const errors = validationResult(req);

    if(!errors.isEmpty()){
        const error = new Error('Wrong data!');
        error.statusCode = 400;
        return next(error);
    }

    const token = req.get('Authorization').split(' ')[1];
    const collectionName = req.params.collectionName;
    const language = req.body.language;
    const category = req.body.category;
    const word = req.body.word;
    const translatedWord = req.body.translatedWord;
    const example = req.body.example;
    const translatedExample = req.body.translatedExample;
    req.file ? imgPath = req.file.path : imgPath = null;
    
    try{
        decodedToken = jwt.verify(token, 'flashcardsproject');
        const author = decodedToken.nick;
        const flashcardExist = await Flashcard.findOne({author : author, word : word, translatedWord : translatedWord, set : collectionName});
        if(flashcardExist){
            const error = new Error('This flashcard already exist in this set!');
            error.statusCode = 400;
            throw(error);
        }
        const newFlashcard = new Flashcard({
            set : collectionName,
            language : language,
            category : category,
            word : word,
            translatedWord : translatedWord,
            example : example,
            translatedExample : translatedExample,
            imgPath : imgPath,
            author : author
        })
        let collectionExist = await FlashcardsCollection.findOne({author: author, collectionName : collectionName});
        if(!collectionExist){
            collectionExist = await new FlashcardsCollection({
                collectionName : collectionName,
                author : author,
                flashcards : [newFlashcard]
            })
        }
        else collectionExist.flashcards.push(newFlashcard);
        await collectionExist.save();
        await newFlashcard.save();
    } catch(error){
        if(!error.statusCode) error.statusCode = 500;
        return next(error);
    }
    res.status(201).json('Flashcard added succesful!')
}

