import {PlayerDto} from '../../player/models/player-dto.model';

export interface Organization
{
  id: number;
  name: string;
  foundationYear: number;
  roster: PlayerDto[];
}
