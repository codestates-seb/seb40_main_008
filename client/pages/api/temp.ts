import { NextApiRequest, NextApiResponse } from "next";

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  const thumbnail = req.body;
  const disposition = res.getHeader("Content-Disposition");
  //const data = JSON.parse(req.body);
  //const data = req.body;
  console.log("req", thumbnail);
  console.log("d", disposition);

  //console.log("h", data.file);

  res.status(200).json({ text: "Hello" });
}
