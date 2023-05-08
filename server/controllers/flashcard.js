const Flashcard = require('../models/flashcard');
const FlashcardsCollection = require('../models/flashcardsCollection');
const { validationResult } = require('express-validator');

exports.addFlashCard = async(req, res, next) => {

    const errors = validationResult(req);

    if(!errors.isEmpty()){
        const error = new Error('Wrong data!');
        error.statusCode = 400;
        return next(error);
    }

    const collectionName = req.params.collectionName;
    const language = req.body.language;
    const category = req.body.category;
    const word = req.body.word;
    const translatedWord = req.body.translatedWord;
    const example = req.body.example;
    const translatedExample = req.body.translatedExample;
    req.file ? imgPath = req.file.path : imgPath = null;
    const author = req.params.nick;
    
    try{
        const flashcardExist = await Flashcard.findOne({word : word, translatedWord : translatedWord});
        if(flashcardExist){
            const error = new Error('This flashcard already exist in this set!');
            error.statusCode = 400;
            throw(error);
        }
        const newFlashcard = new Flashcard({
            collection : collectionName,
            language : language,
            category : category,
            word : word,
            translatedWord : translatedWord,
            example : example,
            translatedExample : translatedExample,
            imgPath : imgPath,
            author : author
        })
        const collectionExist = await Flashcard.findOne({collectionName : collectionName});
        if(!collectionExist){
            const newCollection = await new FlashcardsCollection({
                collectionName : collectionName,
                author : author,
                flashcards : [].push(newFlashcard)
            })
        }
        else{
            
        }
        await newFlashcard.save();
    } catch(error){
        if(!error.statusCode) error.statusCode = 500;
        return next(error);
    }
    res.status(201).json('Flashcard added succesful!')
}

