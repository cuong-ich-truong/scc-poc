import React, { memo } from 'react';
import 'regenerator-runtime/runtime';
import { CCTV } from '../../../types/CCTV';
import ReactHlsPlayer from 'react-hls-player';

export interface Props {
  cctv: CCTV;
}
const CCTVPlayer: React.FC<Props> = memo(({ cctv }) => {
  return (
    <div className="cctv-container">
      {/* @ts-ignore*/}
      <ReactHlsPlayer
        id={`cctv-${cctv.id}`}
        src={cctv.streamUrl}
        autoPlay={true}
        controls={true}
        width="100%"
        height="auto"
        muted
      />
    </div>
  );
});
export default CCTVPlayer;
