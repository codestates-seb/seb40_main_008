import { NextApiRequest, NextApiResponse } from 'next';
import { deleteCookie, removeCookies } from 'cookies-next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	console.log('res: ', res);
	console.log('catch_error: ', req);
	const body = JSON.parse(req.body);
	console.log('body: ', body);

	res.status(200).json({ message: 'success' });



}

/**
 * url:
 * time:
 * errorMsg:
 * statusCode:
 *
 * 나는협업도 생각한다. 협업도 중요하지만, (소프트 스킬도 중요하는 나다)
 * 개발자가 무조건 협업하는 존재는 X.
 * 이 개발자가 그래도 자기 밥값을 할 수있을거다 하는 효용.
 * 
 */
