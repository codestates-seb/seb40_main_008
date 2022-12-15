import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    res.status(200).json({
        data: [
            {
                "userId": 1,
                "id": 1,
                "title": "베이킹",
                "completed": false
            },
            {
                "userId": 1,
                "id": 2,
                "title": "소묘",
                "completed": false
            },
            {
                "userId": 1,
                "id": 3,
                "title": "성공",
                "completed": false
            },
            {
                "userId": 1,
                "id": 4,
                "title": "아이패드",
                "completed": true
            },
            {
                "userId": 1,
                "id": 5,
                "title": "주식투자",
                "completed": false
            },
        ]
    });
}