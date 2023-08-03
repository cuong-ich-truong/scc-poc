import 'package:get_it/get_it.dart';
import 'package:scc_poc_app/services/scc_service.dart';

GetIt getIt = GetIt.instance;

void setUpServiceLocator() {
  getIt.registerSingleton<SCCRepository>(SCCServiceRepository());
}