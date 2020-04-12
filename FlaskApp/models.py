from sqlalchemy import create_engine, MetaData, Table

engine = create_engine("<CONNECTION_STRING_HERE>", convert_unicode=True)
metadata = MetaData(bind=engine)


player_stats = Table('player_stats', metadata, autoload=True)