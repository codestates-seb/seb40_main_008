// Generated by https://quicktype.io

export interface IChargeCoin {
  totalCoin: number;
  coinChargeDetails: CoinChargeDetail[];
}

export interface CoinChargeDetail {
  chargeAmount: number;
  paySuccess: boolean | null;
  approvedAt: null | string;
  cancelAmount: number;
  refund: boolean | null;
  canceled_at: null | string;
}
