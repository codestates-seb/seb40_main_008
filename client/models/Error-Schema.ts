import mongoose from 'mongoose';

/* PetSchema will correspond to a collection in your MongoDB database. */
const ErrorSchema = new mongoose.Schema({
	url: {
		/* The name of this pet */

		type: String,
		required: [true, 'Please provide a name for this pet.'],
		maxlength: [60, 'Name cannot be more than 60 characters'],
	},
	component: {
		/* The owner of this pet */

		type: String,
		required: [true, "Please provide the pet owner's name"],
		maxlength: [60, "Owner's Name cannot be more than 60 characters"],
	},
	statusCode: {
		/* Pet's age, if applicable */

		type: Number,
	},
	time: {
		type: String,
	},
});

export default mongoose.models.Error || mongoose.model('Error', ErrorSchema);
